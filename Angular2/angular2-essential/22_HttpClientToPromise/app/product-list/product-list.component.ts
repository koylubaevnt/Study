import { Component, OnInit } from "@angular/core";
import { Product, ProductService } from "../shared/index";
import { Router } from "@angular/router";

@Component({
    moduleId: module.id,
    selector: 'product-list',
    templateUrl: 'product-list.component.html',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css']
})
export class ProductListComponent implements OnInit {

    products: Product[];
    errorMessage: string;

    constructor(private service: ProductService,
        private router: Router) { }

    ngOnInit() {
        this.getProducts();
    }

    createProduct() {
        this.router.navigate(['products', 'create']);
    }

    editProduct(product: Product) {
        this.router.navigate(['products', 'edit', product.id]);
    }

    deleteProduct(product: Product) {
        this.router.navigate(['products', 'delete', product.id]);
    }

    refresh() {
        this.getProducts();
    }

    private getProducts() {
        this.service.getProducts().then(
            products => this.products = products,
            error => this.errorMessage = error
        )
    }
}