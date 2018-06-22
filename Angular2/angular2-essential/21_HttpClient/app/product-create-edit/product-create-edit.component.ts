import { OnInit, Component } from "@angular/core";
import { Product, ProductService } from "../shared/index";
import { ActivatedRoute, Router, Params } from "@angular/router";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";


@Component({
    moduleId: module.id,
    selector: "product-create-edit",
    templateUrl: "product-create-edit.component.html",
    styleUrls: ["../../../node_modules/bootstrap/dist/css/bootstrap.css"]
})
export class ProductCreateEditComponent implements OnInit {

    currentProduct: Product;
    errorMessage: string;
    productForm: FormGroup;

    constructor(private activatedRoute: ActivatedRoute,
        private router: Router,
        private service: ProductService,
        private fb: FormBuilder) { }

    ngOnInit() {
        this.buildForm();
        this.getProductFromRoute();
    }

    checkError(element: string, errorType:string) {
        console.log('element:', element);
        console.log('errorType:', errorType);
        console.log('productForm: ' + this.productForm.get(element).hasError(errorType)  + ", " + this.productForm.get(element).touched, this.productForm);
        return this.productForm.get(element).hasError(errorType) &&
            this.productForm.get(element).touched;
    }

    onSubmit(productForm: FormGroup) {
        this.currentProduct.name = productForm.value.name;
        this.currentProduct.price = productForm.value.price;

        if(this.currentProduct.id) {
            this.service.updateProduct(this.currentProduct).subscribe(
                () => this.goBack(),
                error => this.errorMessage = error
            );
        } else {
            this.service.addProduct(this.currentProduct).subscribe(
                () => this.goBack(),
                error => this.errorMessage = error
            );
        }
    }

    goBack() {
        this.router.navigate(["/products"]);
    }

    private buildForm() {
        this.productForm = this.fb.group({
            name: ["", Validators.required],
            price: ["", Validators.required]
        });
    }

    private getProductFromRoute() {
        this.activatedRoute.params.forEach((params: Params) => {
            let id = params["id"];
            if(id) {
                console.log("id: " , id);
                this.service.getProduct(id).subscribe(
                    product => {
                        console.log("product: " , product);
                        console.log("productForm", this.productForm);
                        this.currentProduct = product;
                        this.productForm.patchValue(this.currentProduct);
                    },
                    error => this.errorMessage = error
                );
            } else {
                this.currentProduct = new Product(null, null, null);
                this.productForm.patchValue(this.currentProduct);
            }
        })
    }
    
}