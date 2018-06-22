import { Component, OnInit } from "@angular/core";
import { Http, RequestOptions, Headers, URLSearchParams } from "@angular/http";

@Component({
    moduleId: module.id,
    templateUrl: 'search-params.component.html',
    selector: 'search-params'
})
export class SearchParamsComponent {

    url: string = 'app/items';

    constructor(private http: Http) { }

    
    clickHandler() {

        let param = new URLSearchParams();
        param.set("a", "1");
        param.set("b", "value");

        // метод get с параметром search будет выполнять запрос по следующему адресу
        // http://localhost:3000/app/items?a=1&b=value
        // Для того чтобы увидеть запрос в браузере закоментируйте настройку InMemmoryWepApi 
        this.http.get(this.url, { search: param }).subscribe(
            result => console.log('success'),
            error => console.log(error.statusText)
        );
    }

}