# recidiffist

`recidiffist` takes structured values over time and tells you *how* they change.

This produces an immutable log of diffs. Diffs are maps with `::added`,
`::removed` and `::changed` keys.

This lets you efficiently tie changing data (say, off a REST API) to a system
that expects immutable data. That immutable data can go into a log manager, or a
database like a la [Datalog][datalog] like [Datomic][datomic] and
[Datascript][datascript] that lets you query not just the current state, but
also the *evolution* of that state efficiently. It supports arbitrarily nested
and structured data automatically by inspecting its structure, with no code to
write.

For example, if you want to monitor a JSON REST API (which you pull as a cron
job, or gets pushed to you via webhooks) and log changes to a log processor or
dump them into [Datascript][datascript] to see how it changed, `recidiffist` is
for you. If you have an audit tool and any kind of change is intrinsically
interesting, `recidiffist` will make you happy.

[datalog]: https://en.wikipedia.org/wiki/Datalog
[datomic]: http://www.datomic.com/
[datascript]: https://github.com/tonsky/datascript

## License

Copyright © Latacora, LLC

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

This is a fork of an earlier project named [kegan][kegan] which was authored by
the same principal author at a previous employer. To the best of our knowledge,
kegan is no longer maintained.

[kegan]: https://github.com/racksec/kegan
