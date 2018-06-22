import { Component } from "@angular/core";
import { User } from "../user";

@Component({
    moduleId: module.id,
    templateUrl: 'user-form3.component.html',
    selector: 'user-form3',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css', 'user-form3.component.css']
})
export class UserForm3Component {
     
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