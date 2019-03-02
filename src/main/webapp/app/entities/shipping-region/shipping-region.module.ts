import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppcommerceSharedModule } from 'app/shared';
import {
    ShippingRegionComponent,
    ShippingRegionDetailComponent,
    ShippingRegionUpdateComponent,
    ShippingRegionDeletePopupComponent,
    ShippingRegionDeleteDialogComponent,
    shippingRegionRoute,
    shippingRegionPopupRoute
} from './';

const ENTITY_STATES = [...shippingRegionRoute, ...shippingRegionPopupRoute];

@NgModule({
    imports: [AppcommerceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ShippingRegionComponent,
        ShippingRegionDetailComponent,
        ShippingRegionUpdateComponent,
        ShippingRegionDeleteDialogComponent,
        ShippingRegionDeletePopupComponent
    ],
    entryComponents: [
        ShippingRegionComponent,
        ShippingRegionUpdateComponent,
        ShippingRegionDeleteDialogComponent,
        ShippingRegionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppcommerceShippingRegionModule {}
