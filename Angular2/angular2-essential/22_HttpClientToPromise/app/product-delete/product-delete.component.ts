import { OnInit, Component } from "@angular/core";
import { Product, ProductService } from "../shared/index";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    moduleId: module.id,
    selector: "product-delete",
    templateUrl: "product-delete.component.html",
    styleUrls: ["../../../node_modules/bootstrap/dist/css/bootstrap.css"]
})
export class ProductDeleteComponent implements OnInit {

    currentProduct: Product;
    errorMessage: string;

    constructor(private activatedRoute: ActivatedRoute,
        private router: Router,
        private service: ProductService) { }

    ngOnInit() {
        let id = this.activatedRoute.snapshot.params["id"];
        console.log("id: ", id);
        if(id) {
            this.service.getProduct(id).then(
                product => {
                    console.log("product: ", product);
                    this.currentProduct = product
                },
                error => this.errorMessage = error
            )
        }
    }

    goBack() {
        this.router.navigate(["/products"]);
    }

    deleteProduct() {
        this.service.deleteProduct(this.currentProduct).then(
            () => this.goBack(),
            error => this.errorMessage = error
        );
    }
    
}