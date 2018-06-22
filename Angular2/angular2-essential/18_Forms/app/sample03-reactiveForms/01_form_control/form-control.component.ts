import { Component, style, OnInit } from "@angular/core";
import { User } from "../user";
import { FormBuilder, FormGroup, FormControl } from "@angular/forms";


@Component({
    moduleId: module.id,
    templateUrl: 'form-control.component.html',
    selector: 'form-control',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css']
})
export class FormControlComponent implements OnInit {
    
    // FormGroup - группа отдельных элементов управления (FormControl)
    // FormControl - класс, который представляет элемент управления
    loginForm: FormGroup;

    ngOnInit() {
        this.loginForm = new FormGroup({
            login: new FormControl("user1"),
            password: new FormControl()
        });
    }

    onSubmit(form) {
        console.log(form.valid);
        console.log(form.value);
    }

}