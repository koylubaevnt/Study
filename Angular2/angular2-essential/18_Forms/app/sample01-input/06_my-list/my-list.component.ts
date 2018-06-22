import { Component } from "@angular/core";

@Component({
    moduleId: module.id,
    templateUrl: 'my-list.component.html',
    selector: 'my-list-sample'
})
export class MyListSampleComponent {
    list: string[] = [];

    add(item: string) {
        this.list.push(item); 
    }

}