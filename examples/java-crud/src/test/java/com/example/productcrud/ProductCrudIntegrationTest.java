package com.example.productcrud;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.productcrud.dto.ProductRequest;
import com.example.productcrud.dto.ProductResponse;
import com.example.productcrud.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@ActiveProfiles("test")
class ProductCrudIntegrationTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void crudLifecycle() {
        ProductRequest request = new ProductRequest(
                "Mechanical Keyboard",
                "Blue switches, ABNT2 layout",
                new BigDecimal("349.90"),
                15
        );

        ResponseEntity<ProductResponse> created = rest.postForEntity("/api/products", request, ProductResponse.class);
        assertThat(created.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(created.getBody()).isNotNull();
        assertThat(created.getBody().name()).isEqualTo("Mechanical Keyboard");

        Long id = created.getBody().id();

        ResponseEntity<ProductResponse> fetched = rest.getForEntity("/api/products/" + id, ProductResponse.class);
        assertThat(fetched.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(fetched.getBody()).isNotNull();
        assertThat(fetched.getBody().price()).isEqualByComparingTo("349.90");

        ProductRequest updated = new ProductRequest(
                "Mechanical Keyboard Pro",
                "Brown switches",
                new BigDecimal("399.90"),
                10
        );

        ResponseEntity<ProductResponse> updatedResponse = rest.exchange(
                "/api/products/" + id,
                HttpMethod.PUT,
                new HttpEntity<>(updated),
                ProductResponse.class
        );
        assertThat(updatedResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updatedResponse.getBody()).isNotNull();
        assertThat(updatedResponse.getBody().name()).isEqualTo("Mechanical Keyboard Pro");

        ResponseEntity<List<ProductResponse>> list = rest.exchange(
                "/api/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(list.getBody()).extracting(ProductResponse::id).contains(id);

        ResponseEntity<Void> deleted = rest.exchange(
                "/api/products/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertThat(deleted.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<Map> missing = rest.getForEntity("/api/products/" + id, Map.class);
        assertThat(missing.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void returnsNotFoundWhenProductMissing() {
        ResponseEntity<Map> response = rest.getForEntity("/api/products/9999", Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).containsEntry("error", ProductService.PRODUCT_NOT_FOUND);
    }

    @Test
    void returnsNotFoundWhenUpdatingMissingProduct() {
        ProductRequest request = new ProductRequest(
                "Missing Product",
                "Should not persist",
                new BigDecimal("10.00"),
                1
        );

        ResponseEntity<Map> response = rest.exchange(
                "/api/products/9999",
                HttpMethod.PUT,
                new HttpEntity<>(request),
                Map.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).containsEntry("error", ProductService.PRODUCT_NOT_FOUND);
    }

    @Test
    void returnsNotFoundWhenDeletingMissingProduct() {
        ResponseEntity<Map> response = rest.exchange(
                "/api/products/9999",
                HttpMethod.DELETE,
                null,
                Map.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).containsEntry("error", ProductService.PRODUCT_NOT_FOUND);
    }

    @Test
    void rejectsInvalidProductPayload() {
        ProductRequest invalid = new ProductRequest("", null, new BigDecimal("0.00"), -1);

        ResponseEntity<Map> response = rest.postForEntity("/api/products", invalid, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsKey("error");
    }

    @Test
    void rejectsPriceBelowMinimum() {
        ProductRequest invalid = new ProductRequest(
                "Cheap Item",
                "Below minimum price",
                new BigDecimal("0.00"),
                1
        );

        ResponseEntity<Map> response = rest.postForEntity("/api/products", invalid, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsKey("error");
    }

    @Test
    void rejectsNegativeStockQuantity() {
        ProductRequest invalid = new ProductRequest(
                "Invalid Stock",
                "Negative quantity",
                new BigDecimal("9.99"),
                -5
        );

        ResponseEntity<Map> response = rest.postForEntity("/api/products", invalid, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsKey("error");
    }

    @Test
    void listsProductsNewestFirst() {
        ProductRequest older = new ProductRequest(
                "Older Product",
                "Created first",
                new BigDecimal("19.99"),
                5
        );
        ProductRequest newer = new ProductRequest(
                "Newer Product",
                "Created second",
                new BigDecimal("29.99"),
                3
        );

        ResponseEntity<ProductResponse> firstCreated = rest.postForEntity("/api/products", older, ProductResponse.class);
        ResponseEntity<ProductResponse> secondCreated = rest.postForEntity("/api/products", newer, ProductResponse.class);

        assertThat(firstCreated.getBody()).isNotNull();
        assertThat(secondCreated.getBody()).isNotNull();

        ResponseEntity<List<ProductResponse>> list = rest.exchange(
                "/api/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        assertThat(list.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(list.getBody())
                .extracting(ProductResponse::name)
                .containsExactly("Newer Product", "Older Product");
    }

    @Test
    void healthEndpointIsUp() {
        ResponseEntity<Map> response = rest.getForEntity("/actuator/health", Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsEntry("status", "UP");
    }
}
