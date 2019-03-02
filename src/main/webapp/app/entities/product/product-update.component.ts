import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from './product.service';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';
import { IAttribute } from 'app/shared/model/attribute.model';
import { AttributeService } from 'app/entities/attribute';
import { IShoppingCard } from 'app/shared/model/shopping-card.model';
import { ShoppingCardService } from 'app/entities/shopping-card';
import { IOrderItems } from 'app/shared/model/order-items.model';
import { OrderItemsService } from 'app/entities/order-items';

@Component({
    selector: 'jhi-product-update',
    templateUrl: './product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
    product: IProduct;
    isSaving: boolean;

    categories: ICategory[];

    attributes: IAttribute[];

    shoppingcards: IShoppingCard[];

    orderitems: IOrderItems[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected productService: ProductService,
        protected categoryService: CategoryService,
        protected attributeService: AttributeService,
        protected shoppingCardService: ShoppingCardService,
        protected orderItemsService: OrderItemsService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ product }) => {
            this.product = product;
        });
        this.categoryService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICategory[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICategory[]>) => response.body)
            )
            .subscribe((res: ICategory[]) => (this.categories = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.attributeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAttribute[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAttribute[]>) => response.body)
            )
            .subscribe((res: IAttribute[]) => (this.attributes = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.shoppingCardService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IShoppingCard[]>) => mayBeOk.ok),
                map((response: HttpResponse<IShoppingCard[]>) => response.body)
            )
            .subscribe((res: IShoppingCard[]) => (this.shoppingcards = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.orderItemsService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IOrderItems[]>) => mayBeOk.ok),
                map((response: HttpResponse<IOrderItems[]>) => response.body)
            )
            .subscribe((res: IOrderItems[]) => (this.orderitems = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.product.id !== undefined) {
            this.subscribeToSaveResponse(this.productService.update(this.product));
        } else {
            this.subscribeToSaveResponse(this.productService.create(this.product));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>) {
        result.subscribe((res: HttpResponse<IProduct>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCategoryById(index: number, item: ICategory) {
        return item.id;
    }

    trackAttributeById(index: number, item: IAttribute) {
        return item.id;
    }

    trackShoppingCardById(index: number, item: IShoppingCard) {
        return item.id;
    }

    trackOrderItemsById(index: number, item: IOrderItems) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
