import { NgModule } from '@angular/core';
import { Component, Directive, ElementRef } from '@angular/core';

@Directive({
    selector: '[popup]'
})
export class PopupDirective {

    constructor(_elRef: ElementRef) {
        console.log(_elRef);
    }
}

@Component({
    selector: 'app-pop-demo',
    template: `
    <div class="ui message" popup>
        <div class="header">
            Learning Directives
        </div>
        
        <p>
            This should be use our Popup directive
        </p>
    </div>
    
    <i class="alarm icon" popup></i>
    `
})
export class PopupDemoComponent2 {

}

@NgModule({
    declarations: [
        PopupDemoComponent2,
        PopupDirective
    ],
    exports: [ PopupDemoComponent2 ]
})
export class PopupDemoComponent2Module {}