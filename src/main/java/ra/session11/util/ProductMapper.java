package ra.session11.util;

import org.springframework.stereotype.Component;
import ra.session11.model.dto.request.ProductRequest;
import ra.session11.model.entity.Product;

@Component
public class ProductMapper {
    public Product mapToEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setImage(product.getImage());
        return product;
    }

}
