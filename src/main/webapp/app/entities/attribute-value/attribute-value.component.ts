import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAttributeValue } from 'app/shared/model/attribute-value.model';
import { AccountService } from 'app/core';
import { AttributeValueService } from './attribute-value.service';

@Component({
    selector: 'jhi-attribute-value',
    templateUrl: './attribute-value.component.html'
})
export class AttributeValueComponent implements OnInit, OnDestroy {
    attributeValues: IAttributeValue[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected attributeValueService: AttributeValueService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.attributeValueService
            .query()
            .pipe(
                filter((res: HttpResponse<IAttributeValue[]>) => res.ok),
                map((res: HttpResponse<IAttributeValue[]>) => res.body)
            )
            .subscribe(
                (res: IAttributeValue[]) => {
                    this.attributeValues = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAttributeValues();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAttributeValue) {
        return item.id;
    }

    registerChangeInAttributeValues() {
        this.eventSubscriber = this.eventManager.subscribe('attributeValueListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
