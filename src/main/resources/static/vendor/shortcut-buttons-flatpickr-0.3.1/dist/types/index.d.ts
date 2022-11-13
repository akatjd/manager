import { Instance as Flatpickr } from 'flatpickr/dist/types/instance.d';
export declare namespace ShortcutButtonsFlatpickr {
    type OnClickSignature = (index: number, fp: Flatpickr) => void;
    type Attributes = {
        [name: string]: string;
    };
    type Button = {
        attributes?: Attributes;
        label: string;
    };
    type Config = {
        button: Button | Button[];
        label?: string;
        onClick?: OnClickSignature | OnClickSignature[];
        theme?: string;
    };
}
export declare function ShortcutButtonsPlugin(config: ShortcutButtonsFlatpickr.Config): (fp: Flatpickr) => {
    onReady: () => void;
    onDestroy: () => void;
};
