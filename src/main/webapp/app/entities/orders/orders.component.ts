import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IOrders } from 'app/shared/model/orders.model';
import { AccountService } from 'app/core';
import { OrdersService } from './orders.service';

@Component({
    selector: 'jhi-orders',
    templateUrl: './orders.component.html'
})
export class OrdersComponent implements OnInit, OnDestroy {
    orders: IOrders[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected ordersService: OrdersService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.ordersService
            .query()
            .pipe(
                filter((res: HttpResponse<IOrders[]>) => res.ok),
                map((res: HttpResponse<IOrders[]>) => res.body)
            )
            .subscribe(
                (res: IOrders[]) => {
                    this.orders = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInOrders();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IOrders) {
        return item.id;
    }

    registerChangeInOrders() {
        this.eventSubscriber = this.eventManager.subscribe('ordersListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
