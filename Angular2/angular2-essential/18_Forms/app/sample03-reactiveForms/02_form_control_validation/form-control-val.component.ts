import { Component } from "@angular/core";
import { FormGroup, FormControl, Validators } from "@angular/forms";

@Component({
    moduleId: module.id,
    templateUrl: 'form-control-val.component.html',
    selector: 'form-control-val',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css']
})
export class FormControlValComponent {
    
    // FormGroup - группа отдельных элементов управления (FormControl)
    // FormControl - класс, который представляет элемент управления
    loginForm: FormGroup;

    ngOnInit() {
        // Validators - клсаа, со статическими методами для валидации.
        // При вызове конструктора FormControl первый параметр, значение поля для ввода
        // второй параметр - валидатор или массив валидаторов.
        this.loginForm = new FormGroup({
            login: new FormControl("user1", Validators.required),
            password: new FormControl("", [ Validators.required, Validators.minLength(7) ])
        });
    }

    onSubmit(form) {
        console.log(form.valid);
        console.log(form.value);
    }
}