import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";

import { PhraseService } from "./shared/phrase.service";

import { HomeComponent } from "./home/home.component";
import { PhrasesModule } from "./phrases/phrases.module";


@NgModule({
    imports: [
        BrowserModule,
        PhrasesModule,
        AppRoutingModule
    ],
    declarations: [AppComponent, HomeComponent],
    providers: [PhraseService],
    bootstrap: [AppComponent]
})
export class AppModule {

}