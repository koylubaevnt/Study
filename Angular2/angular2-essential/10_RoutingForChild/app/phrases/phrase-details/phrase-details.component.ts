import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';

import { Phrase } from '../../shared/phrase';
import { PhraseService } from '../../shared/phrase.service';

@Component({
    moduleId: module.id,
    selector: 'phrase-details',
    templateUrl: 'phrase-details.component.html'
})
export class PhraseDetailsComponent implements OnInit {

    private phrase: Phrase;

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
                .then(result => this.phrase = result) // как только Promise бперейдет в состояние resolved выполним призвоение полученных данных к переменной phrase
        });

        // Snapshot
        // получение начальное значение параметра id
        //let id = +this.activatedRoute.snapshot.params["id"];
        //this.service
        //    .getPhrase(id)
        //    .then(result => this.phrase = result)
        
    }

    goToPhraseList() {
        this.router.navigate(["phrases"]); // перенаправляем пользователя на  PhraseListComponent
    }
 }