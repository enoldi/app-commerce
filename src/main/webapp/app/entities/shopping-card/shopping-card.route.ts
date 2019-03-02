import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ShoppingCard } from 'app/shared/model/shopping-card.model';
import { ShoppingCardService } from './shopping-card.service';
import { ShoppingCardComponent } from './shopping-card.component';
import { ShoppingCardDetailComponent } from './shopping-card-detail.component';
import { ShoppingCardUpdateComponent } from './shopping-card-update.component';
import { ShoppingCardDeletePopupComponent } from './shopping-card-delete-dialog.component';
import { IShoppingCard } from 'app/shared/model/shopping-card.model';

@Injectable({ providedIn: 'root' })
export class ShoppingCardResolve implements Resolve<IShoppingCard> {
    constructor(private service: ShoppingCardService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IShoppingCard> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ShoppingCard>) => response.ok),
                map((shoppingCard: HttpResponse<ShoppingCard>) => shoppingCard.body)
            );
        }
        return of(new ShoppingCard());
    }
}

export const shoppingCardRoute: Routes = [
    {
        path: '',
        component: ShoppingCardComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShoppingCards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ShoppingCardDetailComponent,
        resolve: {
            shoppingCard: ShoppingCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShoppingCards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ShoppingCardUpdateComponent,
        resolve: {
            shoppingCard: ShoppingCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShoppingCards'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ShoppingCardUpdateComponent,
        resolve: {
            shoppingCard: ShoppingCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShoppingCards'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const shoppingCardPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ShoppingCardDeletePopupComponent,
        resolve: {
            shoppingCard: ShoppingCardResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShoppingCards'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
