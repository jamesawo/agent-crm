import {Component, Input, OnInit} from '@angular/core';

@Component({
    selector: 'ngx-page-spacer',
    templateUrl: './page-spacer.component.html',
    styles: [],
})
export class PageSpacerComponent implements OnInit {
    @Input()
    class: string = '';

    constructor() {
    }

    ngOnInit(): void {
    }

}
