import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IShoppingCard } from 'app/shared/model/shopping-card.model';
import { AccountService } from 'app/core';
import { ShoppingCardService } from './shopping-card.service';

@Component({
    selector: 'jhi-shopping-card',
    templateUrl: './shopping-card.component.html'
})
export class ShoppingCardComponent implements OnInit, OnDestroy {
    shoppingCards: IShoppingCard[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected shoppingCardService: ShoppingCardService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.shoppingCardService
            .query()
            .pipe(
                filter((res: HttpResponse<IShoppingCard[]>) => res.ok),
                map((res: HttpResponse<IShoppingCard[]>) => res.body)
            )
            .subscribe(
                (res: IShoppingCard[]) => {
                    this.shoppingCards = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInShoppingCards();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IShoppingCard) {
        return item.id;
    }

    registerChangeInShoppingCards() {
        this.eventSubscriber = this.eventManager.subscribe('shoppingCardListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
