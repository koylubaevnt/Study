import { Component, OnInit } from "@angular/core";
import { Http, Headers, RequestOptions } from "@angular/http";

@Component({
    moduleId: module.id,
    templateUrl: 'request-options.component.html',
    selector: 'request-options'
})
export class RequestOptionsComponent implements OnInit {

    url: string = 'app/items';
    id: number;
    name: string;

    itemArray: any[];

    constructor(private http: Http) { }

    ngOnInit() {
        this.http.get(this.url).subscribe(
            result => this.itemArray = result.json().data,
            error => console.log(error.statusText)
        );
    }

    clickHandler() {
        // определение пользовательских заголовков
        let myHeaders = new Headers({
            'Content-Type': 'application/json',
            'X-MyHeader': 'Hello world'
        });
        // создание опций запроса
        let options = new RequestOptions({ headers: myHeaders });
        // создание объекта для отправки на сервер
        let data = {
            id: this.id,
            name: this.name
        };
        // post запрос с указанием адреса, данных и опций
        // для того чтобы увидеть данные запроса в Developer Tools во вкладке Network
        // закоментируйте строку импорта и настройки модуля InMemoryWebAppModule в root module
        this.http.post(this.url, data, options).subscribe(
            result => {
                let json = result.json();
                console.log(json);
                if (json) 
                    this.itemArray.push(json.data);
            },
            error => console.log(error.statusText)
        );
    }

}