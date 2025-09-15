package ra.session11.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.session11.model.dto.request.ProductRequest;
import ra.session11.model.dto.response.DataResponse;
import ra.session11.model.entity.Product;
import ra.session11.service.ProductService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<DataResponse<?>> addProduct(@Valid @ModelAttribute ProductRequest request, BindingResult bindingResult) {
        ResponseEntity<DataResponse<?>> response = productService.addProduct(request, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(
                    DataResponse
                            .<Map<String, String>>builder()
                            .message("Add product failed")
                            .data(errorMap)
                            .status(400)
                            .build()
            );
        }
        return response;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DataResponse<?>> updateProduct(@Valid @ModelAttribute ProductRequest request, @PathVariable long id) {
        return productService.updateProduct(request,id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DataResponse<String>> deleteProduct(@PathVariable long id) {
        return productService.deleteProduct(id);
    }
    @GetMapping
    public ResponseEntity<DataResponse<Page<Product>>> findAndSearch(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "search",required = false) String search) {
        return productService.findAllAndSearch(PageRequest.of(page, size), search);
    }
    @GetMapping("/productSuggestions")
    public ResponseEntity<DataResponse<List<Product>>> getProductSuggestions() {
            return productService.getProductSuggestions();
    }

}
