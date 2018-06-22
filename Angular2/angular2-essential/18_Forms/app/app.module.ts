import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";

import { AppComponent } from "./app.component";
import { Router, RouterModule } from "@angular/router";
import { routes } from "./app.routes";

import { InputSampleModule } from "./sample01-input/input-sample.module";
import { FormsSampleModule } from "./sample02-forms/forms-sample.module";
import { ReactiveFormsSampleModule } from "./sample03-reactiveForms/reactive-forms-sample.module";


@NgModule({
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes),
        InputSampleModule,
        FormsSampleModule,
        ReactiveFormsSampleModule
    ],
    declarations: [AppComponent],
    bootstrap: [AppComponent]
})
export class AppModule {

}