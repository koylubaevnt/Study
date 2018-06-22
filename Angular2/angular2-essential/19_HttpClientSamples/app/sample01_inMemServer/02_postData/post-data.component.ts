import { Component, OnInit } from "@angular/core";
import { Http } from "@angular/http";

@Component({
    moduleId: module.id,
    templateUrl: 'post-data.component.html',
    selector: 'post-data'
})
export class PostDataComponent implements OnInit {
    
    itemArray: any[];

    id: number;
    name: string;

    constructor(private http: Http) { }

    ngOnInit() {
        this.http.get('app/items').subscribe(
            result => this.itemArray = result.json().data,
            error => console.log(error.statusText)
        );
    }

    clickHandler() {
        this.http.post('app/items', {
            id: this.id,
            name: this.name
        }).subscribe(
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