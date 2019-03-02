import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'product',
                loadChildren: './product/product.module#AppcommerceProductModule'
            },
            {
                path: 'order-items',
                loadChildren: './order-items/order-items.module#AppcommerceOrderItemsModule'
            },
            {
                path: 'department',
                loadChildren: './department/department.module#AppcommerceDepartmentModule'
            },
            {
                path: 'category',
                loadChildren: './category/category.module#AppcommerceCategoryModule'
            },
            {
                path: 'product',
                loadChildren: './product/product.module#AppcommerceProductModule'
            },
            {
                path: 'attribute',
                loadChildren: './attribute/attribute.module#AppcommerceAttributeModule'
            },
            {
                path: 'attribute-value',
                loadChildren: './attribute-value/attribute-value.module#AppcommerceAttributeValueModule'
            },
            {
                path: 'shopping-card',
                loadChildren: './shopping-card/shopping-card.module#AppcommerceShoppingCardModule'
            },
            {
                path: 'orders',
                loadChildren: './orders/orders.module#AppcommerceOrdersModule'
            },
            {
                path: 'order-items',
                loadChildren: './order-items/order-items.module#AppcommerceOrderItemsModule'
            },
            {
                path: 'shipping-region',
                loadChildren: './shipping-region/shipping-region.module#AppcommerceShippingRegionModule'
            },
            {
                path: 'customer',
                loadChildren: './customer/customer.module#AppcommerceCustomerModule'
            },
            {
                path: 'shipping',
                loadChildren: './shipping/shipping.module#AppcommerceShippingModule'
            },
            {
                path: 'tax',
                loadChildren: './tax/tax.module#AppcommerceTaxModule'
            },
            {
                path: 'review',
                loadChildren: './review/review.module#AppcommerceReviewModule'
            },
            {
                path: 'audit',
                loadChildren: './audit/audit.module#AppcommerceAuditModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppcommerceEntityModule {}
