import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule } from "@angular/forms";

import { AppComponent } from "./app.component";
import { AppRoutingModule } from "./app-routing.module";

import { PhraseService } from "./shared/phrase.service";

import { HomeComponent } from "./home/home.component";
import { PhrasesModule } from "./phrases/phrases.module";
import { AdminModule } from "./admin/admin.module";
import { LoginComponent } from "./login/login.component";
import { LoginRoutingModule } from "./login-routing.module";


@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        PhrasesModule,
        AdminModule,
        AppRoutingModule,
        LoginRoutingModule
    ],
    declarations: [AppComponent, HomeComponent, LoginComponent],
    providers: [
        PhraseService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {

}