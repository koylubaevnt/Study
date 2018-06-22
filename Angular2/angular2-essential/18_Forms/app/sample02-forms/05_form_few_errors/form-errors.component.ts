import { Component } from "@angular/core";
import { User } from "../user";

@Component({
    moduleId: module.id,
    templateUrl: 'form-errors.component.html',
    selector: 'form-errors',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css', 'form-errors.component.css']
})
export class FormErrorsComponent {
     
    roles: string[] = ["Guest", "Moderator", "Administrator"];

    model: User = new User(0, "", "");

    submitted: boolean = false;

    onSubmit() {
        console.log('onSubmit()');
        this.submitted = true;
    }

    get diagnostic() {
        return JSON.stringify(this.model);
    }

}