import { Component } from "@angular/core";
import { User } from "../user";
import { FormGroup, FormControl, FormBuilder, Validators } from "@angular/forms";

@Component({
    moduleId: module.id,
    templateUrl: 'form-builder.component.html',
    selector: 'form-builder',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css']
})
export class FormBuilderComponent {
    
    // FormGroup - группа отдельных элементов управления (FormControl)
    // FormControl - класс, который представляет элемент управления
    loginForm: FormGroup;

    constructor(private fb: FormBuilder) { }

    ngOnInit() {
        // FormBuilder - класс, предоставляющий удобный интерфейс для создания экземпляров FormControl
        this.loginForm = this.fb.group({
            login: ["user1", Validators.required],
            password: ["", [Validators.required, Validators.minLength(7)]]
        });
    }

    onSubmit(form) {
        console.log(form.valid);
        console.log(form.value);
    }
}