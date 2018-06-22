import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";

import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";

import { PhraseService } from "./shared/phrase.service";

import { HomeComponent } from "./home/home.component";
import { PhrasesModule } from "./phrases/phrases.module";
import { AuthGuard } from "./auth-guard.service";
import { AdminModule } from "./admin/admin.module";


@NgModule({
    imports: [
        BrowserModule,
        PhrasesModule,
        AdminModule,
        AppRoutingModule
    ],
    declarations: [AppComponent, HomeComponent],
    providers: [
        PhraseService,
        AuthGuard
    ],
    bootstrap: [AppComponent]
})
export class AppModule {

}