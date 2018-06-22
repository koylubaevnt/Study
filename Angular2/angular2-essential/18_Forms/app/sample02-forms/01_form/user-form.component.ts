import { Component, style } from "@angular/core";
import { User } from "../user";


@Component({
    moduleId: module.id,
    templateUrl: 'user-form.component.html',
    selector: 'user-form',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css']
})
export class UserFormComponent {
    roles: string[] = ["Guest", "Moderator", "Administrator"];

    model: User = new User(0, "", "", 0);

    submitted: boolean = false;

    onSubmit() {
        console.log('onSubmit()');
        this.submitted = true;
    }

    get diagnostic() {
        return JSON.stringify(this.model);
    }
}