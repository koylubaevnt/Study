import { Component, ViewChild, AfterViewInit } from "@angular/core";
import { User } from "../user";
import { NgForm } from "@angular/forms";

@Component({
    moduleId: module.id,
    templateUrl: 'user-form5.component.html',
    selector: 'user-form5',
    styleUrls: ['../../../node_modules/bootstrap/dist/css/bootstrap.css', 'user-form5.component.css']
})
export class UserForm5Component implements AfterViewInit {
     
    roles: string[] = ["Guest", "Moderator", "Administrator"];

    model: User = new User(0, "", "");

    submitted: boolean = false;

    // объект с ошибками, которые будут выведены в пользовательском интерфейсе
    formErrors = {
        "name": "",
        "age": ""
    };

    // объект с сообщениями ошибок
    validationMessages = {
        "name" : {
            "required": "Обязательное поле.",
            "minlength": "Значение должно быть не менее 4х символов.",
        },
        "age" : {
            "required": "Обязательное поле.",
        },
    };

    // ViewChild - используем лоя получения доступа к указанному компоненту и его методам
    @ViewChild('userForm')
    userForm: NgForm;

    ngAfterViewInit() {
        this.userForm.valueChanges.subscribe(data => this.onValueChanged(data));
    }

    onValueChanged(data?: any) {
        if (!this.userForm) return;
        let form = this.userForm.form;

        for(let field in this.formErrors) {
            this.formErrors[field] = "";
            // form.get - получение элемента управления
            let control = form.get(field);
        
            if(control && control.dirty && !control.valid) {
                let message = this.validationMessages[field];
                for(let key in control.errors) {
                    this.formErrors[field] += message[key] + " ";
                }
            }
        }
    }

    onSubmit() {
        console.log('onSubmit()');
        this.submitted = true;
    }

}