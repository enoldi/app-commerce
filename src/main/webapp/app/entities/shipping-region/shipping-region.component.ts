import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IShippingRegion } from 'app/shared/model/shipping-region.model';
import { AccountService } from 'app/core';
import { ShippingRegionService } from './shipping-region.service';

@Component({
    selector: 'jhi-shipping-region',
    templateUrl: './shipping-region.component.html'
})
export class ShippingRegionComponent implements OnInit, OnDestroy {
    shippingRegions: IShippingRegion[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected shippingRegionService: ShippingRegionService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.shippingRegionService
            .query()
            .pipe(
                filter((res: HttpResponse<IShippingRegion[]>) => res.ok),
                map((res: HttpResponse<IShippingRegion[]>) => res.body)
            )
            .subscribe(
                (res: IShippingRegion[]) => {
                    this.shippingRegions = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInShippingRegions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IShippingRegion) {
        return item.id;
    }

    registerChangeInShippingRegions() {
        this.eventSubscriber = this.eventManager.subscribe('shippingRegionListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
