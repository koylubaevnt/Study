import { NgModule } from "@angular/core";

//Для выполнения в браузере
import { BrowserModule } from '@angular/platform-browser';

import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ListsModule, routs } from './lists/index'; // использование barrel file

@NgModule({
    imports: [
        BrowserModule,
        ListsModule, //используем модуль с List компонентами
        RouterModule.forRoot(routs)
    ],
    declarations: [AppComponent],
    bootstrap: [AppComponent]
})
export class AppModule {

}