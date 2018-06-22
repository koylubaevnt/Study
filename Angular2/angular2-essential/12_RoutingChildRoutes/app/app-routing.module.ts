import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";

import { HomeComponent } from "./home/home.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: "",
                redirectTo: "home",
                pathMatch: "full"
            },
            { path: "home", component: HomeComponent }
        ])
    ],
    exports: [RouterModule] // делаем reexport модуля для использования директив при маршрутизации
})
export class AppRoutingModule { }