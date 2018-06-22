import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

import { UserForm3Component,
    FormErrorsComponent,
    UserForm4Component,
    UserForm5Component,
    UserForm2Component,
    StylesSampleComponent,
    UserFormComponent,
 } from "./index";


@NgModule({
    imports: [CommonModule, FormsModule],
    declarations: [
       
        UserFormComponent,
        StylesSampleComponent,
        UserForm2Component,
        UserForm3Component,
        FormErrorsComponent,
        UserForm4Component,
        UserForm5Component,
    ]
})
export class FormsSampleModule {}