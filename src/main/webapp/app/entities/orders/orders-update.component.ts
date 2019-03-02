import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from './orders.service';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer';
import { IShipping } from 'app/shared/model/shipping.model';
import { ShippingService } from 'app/entities/shipping';
import { ITax } from 'app/shared/model/tax.model';
import { TaxService } from 'app/entities/tax';

@Component({
    selector: 'jhi-orders-update',
    templateUrl: './orders-update.component.html'
})
export class OrdersUpdateComponent implements OnInit {
    orders: IOrders;
    isSaving: boolean;

    customers: ICustomer[];

    shippings: IShipping[];

    taxes: ITax[];
    createdOnDp: any;
    shippedOnDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected ordersService: OrdersService,
        protected customerService: CustomerService,
        protected shippingService: ShippingService,
        protected taxService: TaxService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ orders }) => {
            this.orders = orders;
        });
        this.customerService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICustomer[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICustomer[]>) => response.body)
            )
            .subscribe((res: ICustomer[]) => (this.customers = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.shippingService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IShipping[]>) => mayBeOk.ok),
                map((response: HttpResponse<IShipping[]>) => response.body)
            )
            .subscribe((res: IShipping[]) => (this.shippings = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.taxService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ITax[]>) => mayBeOk.ok),
                map((response: HttpResponse<ITax[]>) => response.body)
            )
            .subscribe((res: ITax[]) => (this.taxes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.orders.id !== undefined) {
            this.subscribeToSaveResponse(this.ordersService.update(this.orders));
        } else {
            this.subscribeToSaveResponse(this.ordersService.create(this.orders));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrders>>) {
        result.subscribe((res: HttpResponse<IOrders>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCustomerById(index: number, item: ICustomer) {
        return item.id;
    }

    trackShippingById(index: number, item: IShipping) {
        return item.id;
    }

    trackTaxById(index: number, item: ITax) {
        return item.id;
    }
}
