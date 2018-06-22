import { Injectable } from "@angular/core";

// Декоратор указывает, что данный класс можно создавать с помощью инжектора.
// Декораторы @Cmponent, @Pipe, @Directive производные от Injectable
// Для DataService декоратор, в данном случае, не обязателен, так как сам сервис не создается с помощью инжектора.
// Как только у сервиса появятся зависимости (будет добавлен конструктор, который требует определения зависимостей)
// Angular начнет выбрасывать исключение.

// По соглашению декоратор @Injectable добавляется всем сервисам даже если не является обязательным
// 1) Нет необходимости добавлять декоратор, когда появятся зависимости
// 2) Все сервисы имеют одинаковый вид
@Injectable() 
export class DataService {

    getData() {
        let items:string[] = [];
        for (let i = 0; i < 10; i++) {
            items[i] = "Item " + i;
        }
        return items;
    }
    
}

