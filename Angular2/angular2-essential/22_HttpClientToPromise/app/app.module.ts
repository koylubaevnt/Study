import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpModule } from "@angular/http";
import { RouterModule } from "@angular/router";
import { ReactiveFormsModule } from "@angular/forms";

import { AppComponent } from "./app.component";
import { ProductListComponent } from "./product-list/product-list.component";
import { ProductService } from "./shared/index";
import { routes } from "./app.routes";
import { ProductCreateEditComponent } from "./product-create-edit/product-create-edit.component";
import { ProductDeleteComponent } from "./product-delete/product-delete.component";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        ReactiveFormsModule,
        RouterModule.forRoot(routes)
    ],
    declarations: [AppComponent, ProductListComponent, ProductCreateEditComponent, ProductDeleteComponent],
    providers: [ ProductService ],
    bootstrap: [AppComponent]
})
export class AppModule {

}