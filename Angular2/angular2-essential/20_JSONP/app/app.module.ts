import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpModule, JsonpModule } from "@angular/http";

import { AppComponent } from "./app.component";
import { WikiComponent } from "./wiki/wiki.component";
import { WiKiService } from "./wiki/wiki.service";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        JsonpModule
    ],
    declarations: [AppComponent, WikiComponent],
    providers: [WiKiService],
    bootstrap: [AppComponent]
})
export class AppModule {

}