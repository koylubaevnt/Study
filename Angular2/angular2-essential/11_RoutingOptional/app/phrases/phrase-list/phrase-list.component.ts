import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { Phrase } from '../../shared/phrase';
import { PhraseService } from '../../shared/phrase.service';

@Component({
    moduleId: module.id,
    selector: 'phrase-list',
    templateUrl: 'phrase-list.component.html',
    styleUrls: ['phrase-list.component.css']
})
export class PhraseListComponent implements OnInit {

    selectedId: number;
    phrases: Phrase[];

    constructor(private routter: Router,
        private activatedRoute: ActivatedRoute, 
        private phraseService: PhraseService) { }

    ngOnInit() {
        this.activatedRoute.params.forEach((params: Params) => {
            this.selectedId = +params["id"];
            
            this.phraseService  // обращаемся к сервису
                .getAll()       // получаем Promise
                .then(result => this.phrases = result); // как только Promise перейдет в состояние resolved, вызываем операцию инициализации свойства phrases 
        });
    }

    isSelected(phrase: Phrase) {
        return phrase.id == this.selectedId;
    }

    onSelect(selected: Phrase) {
        // при клике по элементу списка перенаправим пользователя по адресу /phrase/id
        // адрес с обязательным параметром указан в настройках маршрутизации в файле app.routes.ts
        this.routter.navigate(["phrase", selected.id]);
    }
 }