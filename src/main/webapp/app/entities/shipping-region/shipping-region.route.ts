import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ShippingRegion } from 'app/shared/model/shipping-region.model';
import { ShippingRegionService } from './shipping-region.service';
import { ShippingRegionComponent } from './shipping-region.component';
import { ShippingRegionDetailComponent } from './shipping-region-detail.component';
import { ShippingRegionUpdateComponent } from './shipping-region-update.component';
import { ShippingRegionDeletePopupComponent } from './shipping-region-delete-dialog.component';
import { IShippingRegion } from 'app/shared/model/shipping-region.model';

@Injectable({ providedIn: 'root' })
export class ShippingRegionResolve implements Resolve<IShippingRegion> {
    constructor(private service: ShippingRegionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IShippingRegion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ShippingRegion>) => response.ok),
                map((shippingRegion: HttpResponse<ShippingRegion>) => shippingRegion.body)
            );
        }
        return of(new ShippingRegion());
    }
}

export const shippingRegionRoute: Routes = [
    {
        path: '',
        component: ShippingRegionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShippingRegions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ShippingRegionDetailComponent,
        resolve: {
            shippingRegion: ShippingRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShippingRegions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ShippingRegionUpdateComponent,
        resolve: {
            shippingRegion: ShippingRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShippingRegions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ShippingRegionUpdateComponent,
        resolve: {
            shippingRegion: ShippingRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShippingRegions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const shippingRegionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ShippingRegionDeletePopupComponent,
        resolve: {
            shippingRegion: ShippingRegionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ShippingRegions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
