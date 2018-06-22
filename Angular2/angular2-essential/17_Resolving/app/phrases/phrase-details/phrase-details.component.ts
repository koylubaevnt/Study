import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { Phrase } from '../../shared/phrase';
import { PhraseService } from '../../shared/phrase.service';
import { CanComponentDeactivate } from '../../shared/can-deactivate-guard.service';

@Component({
    moduleId: module.id,
    selector: 'phrase-details',
    templateUrl: 'phrase-details.component.html'
})
export class PhraseDetailsComponent implements OnInit,CanComponentDeactivate {

    private phrase: Phrase;

    // поля, в которые будут скопированы значения для редактирования
    editValue: string;
    editLanguage: string;

    // ActivatedRoute - содержит информацию о маршруте связанную с компонентом, который загружен
    constructor(private router: Router,
        private activatedRoute: ActivatedRoute,
        private service: PhraseService
    ) { }

    ngOnInit() {
        // phrase - инициализируется с помощью PhraseDetailsResolve объекта, который указан
        // в настройках системы маршрутизации.
        // на activatedRoute обращаемся к свойству data, а не param как в прошлых примерах.
        // Данный компонент избавился от зависимостей PhraseService
        this.activatedRoute.data.forEach((data: { phrase: Phrase}) => {
            this.phrase = data.phrase;
            this.editValue = data.phrase.value;
            this.editLanguage = data.phrase.language;
        });
    }

    // метод для сохранения изменений сделанных пользователем.
    save() {
        this.phrase.value = this.editValue;
        this.phrase.language = this.editLanguage;
        this.goToPhraseList();
    }

    goToPhraseList() {
        let pId = this.phrase ? this.phrase.id : null;
        // используя относительного пути при перенаправлении пользователя.
        // юю. подняться на уровень выше
        this.router.navigate(["../", { id: pId, param1: "test", param2: 123 }], { relativeTo: this.activatedRoute }); // перенаправляем пользователя на  PhraseListComponent
    }

    // метод для проверки возможности перенаправления пользователя на другой маршрут
    // если метод возвращает true перенаправление возможно
    // если метод вернет false пользователь получит уведомление с просьбой подтвердить переход
    // Данный метод будет использоваться при работе с CanDeactivateGuard (shared/can-deactivate-guard.service.ts)
    canDeactivate(): Promise<boolean> | boolean {
        if(!this.phrase) {
            return true;
        }
        if(this.phrase.value == this.editValue && this.phrase.language == this.editLanguage) {
            return true;
        }

        return confirm('Вы не сохранили изменения. Уйти со страницы?');
    }
 }