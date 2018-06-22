import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";

import { LoginComponent } from "./login/login.component";
import { AuthGuard } from "./shared/auth-guard.service";
import { AuthService } from "./shared/auth.service";

@NgModule({
    imports: [
        RouterModule.forChild([
            { path: "login", component: LoginComponent }
        ])
    ],
    exports: [RouterModule], // делаем reexport модуля для использования директив при маршрутизации
    providers: [
        AuthGuard,
        AuthService
    ]
})
export class LoginRoutingModule { }