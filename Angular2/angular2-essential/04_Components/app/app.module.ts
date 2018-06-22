import { NgModule } from "@angular/core";

//Для выполнения в браузере
import { BrowserModule } from '@angular/platform-browser';

import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { List1Component } from './list1/list1.component';
import { List2Component } from './list2/list2.component';

@NgModule({
    imports: [
        BrowserModule,
        RouterModule.forRoot([
            // Настройка маршрутов
            { path: 'list1', component: List1Component },
            { path: 'list2', component: List2Component },
            { path: '', redirectTo: 'list1', pathMatch: 'full' }
        ])
    ],
    declarations: [AppComponent, List1Component, List2Component],
    bootstrap: [AppComponent]
})
export class AppModule {

}