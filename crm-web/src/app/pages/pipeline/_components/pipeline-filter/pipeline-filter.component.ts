import {Component, OnInit} from '@angular/core';

@Component({
    selector: 'ngx-pipeline-filter',
    templateUrl: './pipeline-filter.component.html',
    styles: [
        `
            nb-checkbox {
                margin-bottom: 1rem;
            }

            .form-inline [fullWidth] {
                flex: 1;
            }

            .form-inline > * {
                margin: 0 1.5rem 1.5rem 0;
            }

            nb-card.inline-form-card nb-card-body {
                padding-bottom: 0;
            }
        `,
    ],
})
export class PipelineFilterComponent implements OnInit {

    constructor() {
    }

    ngOnInit(): void {
    }
}
