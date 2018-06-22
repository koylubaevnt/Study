import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { ManagePhrasesComponent } from "./manage-phrases/manage-phrases.component";
import { ManageUsersComponent } from "./manage-users/manage-users.component";
import { AdminHomeComponent } from "./admin-home/admin-home.component";
import { AuthGuard } from "../shared/auth-guard.service";

@NgModule({
    imports: [
        // Определение маршрутов для feature модуля
        // Метод forRoot должен использоваться только в AppModule
        RouterModule.forChild([
            { 
                path: "admin",
                component: AdminHomeComponent,
                canActivate: [ AuthGuard ], // если Guard вернет false маршрут не активируется
                children: [{
                    path: "",
                    children: [
                        { path: "phrases", component: ManagePhrasesComponent },
                        { path: "users", component: ManageUsersComponent },
                        { path: "", redirectTo: "phrases", pathMatch: "full" }
                    ]
                }]
            }
        ])
    ],
    exports: [
        RouterModule
    ]
})
export class AdminRoutingModule {}