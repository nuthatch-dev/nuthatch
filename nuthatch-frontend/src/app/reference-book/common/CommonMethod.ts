export function dateFormat(d: Date): string {
  let date = new Date(d);
  let year: string = date.getFullYear().toString();
  let month: string = (date.getMonth() + 1).toString().padStart(2, '0');
  let day: string = date.getDate().toString().padStart(2, '0');
  return year + "-" + month + "-" + day
}
