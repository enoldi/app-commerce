import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppcommerceSharedModule } from 'app/shared';
import {
    ShoppingCardComponent,
    ShoppingCardDetailComponent,
    ShoppingCardUpdateComponent,
    ShoppingCardDeletePopupComponent,
    ShoppingCardDeleteDialogComponent,
    shoppingCardRoute,
    shoppingCardPopupRoute
} from './';

const ENTITY_STATES = [...shoppingCardRoute, ...shoppingCardPopupRoute];

@NgModule({
    imports: [AppcommerceSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ShoppingCardComponent,
        ShoppingCardDetailComponent,
        ShoppingCardUpdateComponent,
        ShoppingCardDeleteDialogComponent,
        ShoppingCardDeletePopupComponent
    ],
    entryComponents: [
        ShoppingCardComponent,
        ShoppingCardUpdateComponent,
        ShoppingCardDeleteDialogComponent,
        ShoppingCardDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppcommerceShoppingCardModule {}
