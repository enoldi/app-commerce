import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IOrderItems } from 'app/shared/model/order-items.model';
import { OrderItemsService } from './order-items.service';
import { IOrders } from 'app/shared/model/orders.model';
import { OrdersService } from 'app/entities/orders';

@Component({
    selector: 'jhi-order-items-update',
    templateUrl: './order-items-update.component.html'
})
export class OrderItemsUpdateComponent implements OnInit {
    orderItems: IOrderItems;
    isSaving: boolean;

    orders: IOrders[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected orderItemsService: OrderItemsService,
        protected ordersService: OrdersService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ orderItems }) => {
            this.orderItems = orderItems;
        });
        this.ordersService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IOrders[]>) => mayBeOk.ok),
                map((response: HttpResponse<IOrders[]>) => response.body)
            )
            .subscribe((res: IOrders[]) => (this.orders = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.orderItems.id !== undefined) {
            this.subscribeToSaveResponse(this.orderItemsService.update(this.orderItems));
        } else {
            this.subscribeToSaveResponse(this.orderItemsService.create(this.orderItems));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderItems>>) {
        result.subscribe((res: HttpResponse<IOrderItems>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackOrdersById(index: number, item: IOrders) {
        return item.id;
    }
}
