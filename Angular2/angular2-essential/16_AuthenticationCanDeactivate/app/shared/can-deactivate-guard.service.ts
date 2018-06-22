import { Injectable } from "@angular/core";
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanDeactivate } from "@angular/router";
import { Observable } from "rxjs/Observable";
import { AuthService } from "./auth.service";

// Интерфейс, который необходимо реализовать в компоненте, который может отменить перенаправление
// в случае необходимости.
export interface CanComponentDeactivate {
    // если метод вернет true перенаправление возможно, false - нет.
    canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean; 
}

// Guard для настроек маршрута
// Guard может работать с объектоами, которые реальизовали интерфейс CanComponentDeactivate
// Данный класс используется в настрйоках маршрутиа в phrases-routing.module.ts
@Injectable()
export class CanDeactivateGuard  implements CanDeactivate<CanComponentDeactivate> {

    canDeactivate(component: CanComponentDeactivate) {
        
        // проверяем наличие метода canDeactivate и вызов его
        return component.canDeactivate ? component.canDeactivate() : true;
    }

}