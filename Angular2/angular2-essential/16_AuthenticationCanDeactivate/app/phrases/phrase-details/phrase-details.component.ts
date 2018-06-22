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
        // params - параметры текущего маршрута. Данное свойство является Observable объектом
        // Если параметры будут изменены - произойдет событие и компонент узнает об изменениях.

        // Observable params
        // forEach - устанавливаем обработчик на каждое изменение params
        this.activatedRoute.params.forEach((params: Params) => {
            let id = +params["id"]; //Конвертируем значение параметра id в тип number
            this.service
                .getPhrase(id)  // обращаемся к сервису и запрашиваем фразу по id. Получаем Promise из Phrase
                .then(result => {
                    this.phrase = result;
                    this.editValue = this.phrase.value;
                    this.editLanguage = this.phrase.language;
                }) // как только Promise бперейдет в состояние resolved выполним призвоение полученных данных к переменной phrase
        });

        // Snapshot
        // получение начальное значение параметра id
        //let id = +this.activatedRoute.snapshot.params["id"];
        //this.service
        //    .getPhrase(id)
        //    .then(result => this.phrase = result)
        
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