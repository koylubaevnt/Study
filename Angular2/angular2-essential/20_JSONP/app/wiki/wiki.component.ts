import { Component } from "@angular/core";
import { WiKiService } from "./wiki.service";

@Component({
    moduleId: module.id,
    templateUrl: 'wiki.component.html',
    selector: 'wiki'
})
export class WikiComponent {
    
    items: any[] = [];
    
    constructor(private wikiService: WiKiService) { }

    search(term: string) {
        this.wikiService.search(term).subscribe(
            response => this.items = response,
            error => console.log(error)
        );
    }
}