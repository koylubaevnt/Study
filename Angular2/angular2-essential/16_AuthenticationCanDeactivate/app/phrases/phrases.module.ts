import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PhraseListComponent } from "./phrase-list/phrase-list.component";
import { PhraseDetailsComponent } from "./phrase-details/phrase-details.component";
import { PhrasesRoutingModule } from "./phrases-routing.module";
import { FormsModule } from "@angular/forms";


@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        PhrasesRoutingModule // настройки маршрутизации для модуля PhrasesModule
    ],
    declarations: [
        PhraseListComponent,
        PhraseDetailsComponent
    ]
})
export class PhrasesModule { }